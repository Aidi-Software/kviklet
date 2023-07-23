package com.example.executiongate.controller

import com.example.executiongate.db.GroupAdapter
import com.example.executiongate.db.User
import com.example.executiongate.db.UserAdapter
import com.example.executiongate.db.UserEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CreateUserRequest(
    @field:NotBlank
    @field:Size(min = 3, max = 50)
    val email: String,

    @field:NotBlank
    @field:Size(min = 6, max = 50)
    val password: String,

    @field:NotBlank
    @field:Size(min = 1, max = 50)
    val fullName: String
)

data class EditUserRequest(
    @field:Size(min = 3, max = 50)
    val email: String? = null,

    @field:Size(min = 1, max = 50)
    val fullName: String? = null,

    @field:Size(min = 1, max = 50)
    val permissionString: String? = null,

    val groups: List<String>? = null
)

data class UserResponse(
    val id: String,
    val email: String,
    val fullName: String?,
    val permissionString: String,
    val groups: List<GroupResponse>
) {
    constructor(user: User) : this(
        id = user.id,
        email = user.email,
        fullName = user.fullName,
        permissionString = permissionsTopermissionString(user.permissions),
        groups = user.groups.map { GroupResponse.fromDto(it) }
    )
}

data class UsersResponse(
    val users: List<UserResponse>
) {
    companion object {
        fun fromUsers(users: List<User>): UsersResponse {
            return UsersResponse(users.map { UserResponse(it) })
        }
    }
}


@RestController()
@Validated
@RequestMapping("/users")
class UserController(
    private val userAdapter: UserAdapter,
    private val passwordEncoder: PasswordEncoder,
    private val groupAdapter: GroupAdapter
) {

    @PostMapping("/")
    fun createUser(@RequestBody @Valid userRequest: CreateUserRequest): UserResponse {
        val user = User(
            email = userRequest.email,
            fullName = userRequest.fullName,
            password = passwordEncoder.encode(userRequest.password)
        )
        return UserResponse(userAdapter.createUser(user))
    }

    @GetMapping("/")
    fun getUsers(): UsersResponse {
        return UsersResponse.fromUsers(userAdapter.listUsers())
    }

    @PatchMapping("/{id}")
    fun patchUser(@PathVariable id: String, @RequestBody @Valid userRequest: EditUserRequest): UserResponse {
        val user = userAdapter.findById(id)
        // Update user details if present in the request
        val updatedUser = user.copy(
            email = userRequest.email ?: user.email,
            fullName = userRequest.fullName ?: user.fullName,
            groups = userRequest.groups?.let { groupAdapter.findByIds(it).toSet() } ?: user.groups
        )
        val savedUser = userAdapter.updateUser(updatedUser)
        return UserResponse(savedUser)
    }
}


