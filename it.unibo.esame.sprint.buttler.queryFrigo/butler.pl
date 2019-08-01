%====================================================================================
% butler description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxbutler, "localhost",  "MQTT", "0" ).
 qactor( fridge_dummy, ctxbutler, "it.unibo.fridge_dummy.Fridge_dummy").
  qactor( frontend_dummy, ctxbutler, "it.unibo.frontend_dummy.Frontend_dummy").
  qactor( butler, ctxbutler, "it.unibo.butler.Butler").
