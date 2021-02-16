@echo off
set a=0
:loop
for /f %%p in ('wmic cpu get loadpercentage') do set /A a=%%p


curl -H "Content-type: application/json" -d {\"cpu"\":\"%a%"\",\"instance"\":\"server1"\"} localhost:8888/v1/cpu
 timeout /t 1
 goto loop