import React from "react";
import Button from "../../../components/Button";
import { getSQLDumpRequest } from "../../../api/ExecutionRequestApi";

export default function DatabaseDump(props: {
  onConfirm: () => Promise<void>;
}) {
  const handleConfirmClick = async () => {
    await props.onConfirm();
  };

  return (
    <div className="w-2xl flex flex-col rounded-lg border border-slate-300 bg-slate-50 p-5 shadow dark:border-none dark:bg-slate-950">
      <h1 className="text-lg font-semibold">Getting SQL dump?</h1>
      <div className="flex justify-between mt-5">
        <Button onClick={handleConfirmClick} type="button">
          Confirm
        </Button>
      </div>
    </div>
  );
}
