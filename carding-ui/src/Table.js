import "./App.css";
import React, { useEffect, useState } from "react";

export default function Table() {
  const [logs, setLogs] = useState();

  useEffect(() => {
    const getApiData = async () => {
      const response = await fetch("http://localhost:8084/logs").then(
        (response) => response.json()
      );

      setLogs(response);
    };

    getApiData();
  }, []);

  return (
    <table>
      <thead>
        <tr>
          <th>Сообщение</th>
          <th>Сервис</th>
          <th>Тип</th>
          <th>Время</th>
        </tr>
      </thead>
      <tbody>
        {logs?.map((log) => (
          <tr>
            <td className="tdLeft">{log.content}</td>
            <td>{log.service}</td>
            <td className={log.level == "ERROR" ? "red" : "green"}>
              {log.level}
            </td>
            <td>{log.time}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
