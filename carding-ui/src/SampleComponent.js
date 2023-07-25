import React, { useEffect, useState } from "react";
import SockJS from "sockjs-client";

export default function SampleComponent() {
  const [client, setConnection] = useState();

  useEffect(() => {
    const connect = () => {
      const socket = () => {
        return new SockJS("/session");
      };
      // stompClient = Stomp.over(socket);
      // stompClient.connect({}, function (frame) {
      //     // setConnected(true);
      //     // console.log('Connected: ' + frame);
      //     stompClient.subscribe('/topic/greetings', function (greeting) {
      //         showGreeting(JSON.parse(greeting.body).content);
      //     });
      // });

      setConnection(socket);
    };

    connect();
  }, []);

  // sock.onopen = function () {
  //   console.log("open");
  //   sock.send("test");
  // };

  // sock.onmessage = function (e) {
  //   console.log("message", e.data);
  //   sock.close();
  // };

  // sock.onclose = function () {
  //   console.log("close");
  // };

  if (client != null) {
    return <div>Не похуй</div>;
  }

  return <div>похуй</div>;
}
