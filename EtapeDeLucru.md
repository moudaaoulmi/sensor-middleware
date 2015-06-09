**Prima etapa consta in dezvoltarea arhitecturii blackboard**. In cadrul acestei etape, este necesar sa se construiasca un „miniprototip” care sa functioneze doar pe baza data-stream-ului provenit de la senzorul current-cost.

  * Dezvoltare dispatcher-sensor agent (vezi sectiunea 3.4 „Dispatcher Sensor-Stream Agent” din articol). Rolul agentului DSSA ete de a lectura fluxul de date care intra prin intermediul portului USB. Acest agent citeste toate mesajele primite, dupa care creeaza pachete de tip sensor-record pe care le depune in bufferul sau. Agentii-senzor lectureaza periodic acest buffer, si preiau de acolo inregistrarile care ii intereseaza. (Preluarea se face intr-un mod brutal, inregistrarea fiind apoi stearsa).
  * Modelarea fisierului sensorFormats.xml; introducere / modificare / stergere de inregistrari din sensorFormats.xml.
  * Management-ul retelei de senzori se va realiza tot la nivelul DSSA. Va trebui construita o tabela in care se vor inregistra toti senzorii fizici.
  * SocketProxyAgent vs Jess pentru realizarea conexiunii. (+ teste / studiu pentru a determina care este solutia optima).

**A doua etapa** consta in dezvoltarea structurii unui agent-senzor. Vezi in acest sens sectiunea 3.6 „Sensor Agent” din articol. Va trebui implementat sistemul de tip workflow pentru acesti agenti. Cercetarea va trebui sa se orienteze spre WADE.  Operatorii asupra unui stream de date provenit de la senzori sunt dezvoltati in cadrul proiectului

https://sites.google.com/site/sensormiddleware/

http://code.google.com/p/uvtsensormiddleware

**A treia etapa presupune dezvoltarea agentilor-interpretori**. Acesti agenti se ocupa doar cu interpretarea datelor care se afla stocate in baza de date, si nu mai executa nici o modificare asupra acestora.

Interpretari se vor efectua asupra:
- datelor provenite de la senzorii de tip current-cost;

- datelor provenite de la senzorii de masurare a consumului de apa;

- datelor provenite de la senzorii de masurare a consumului de gaz;

- datelor provenite de la senzorii de tip accelerometru fixati pe geam / usa;

- datelor provenite de la senzorii de tip RFID;

- datelor provenite de la senzorii de tip temperatura;

- datelor provenite de la senzorii de tip prezenta;

- datelor provenite de la senzorii de tip lumina;

- datelor provenite de la senzorii de tip liquid level;

**Etapa a patra se va concretiza prin procesul de dezvoltare al agentilor care realizeaza agregarea datelor din blackboard**. Este vorba despre senzorii logici care proceseaza datele din blackboard: LogicSensorAgent (LSA). Acesti agenti vor trebui sa realizeze interferente intre datele care se afla depuse in blackboard, in urma procesului de interpretare dezvoltat in etapa anterioara. Important este ca in cadrul acestei etape sa se integreze activitatea desfasurata de echipa „Ambiental Learning” in cadrul sistemului multi-agent dezvoltat.

https://sites.google.com/site/ambientallearning

http://code.google.com/p/ambientallearning

In cadrul celei de-a cincea etape se vor efectua teste asupra prototipului dezvoltat.
