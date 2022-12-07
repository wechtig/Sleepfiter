create table sleep (
    id integer primary key,
    patientName varchar(20),
    startTime varchar(20),
    endTime varchar(20),
    minutesSlept integer,
    minutesWakedUp integer,
    standUp integer,
    timeInBed integer,
    timeRem integer,
    deepSleep integer,
    lightSleep integer
);