
SELECT alluser.name, COUNT(alltask.name) as Count FROM alluser LEFT OUTER JOIN alltask ON alluser.id = alltask.name GROUP BY alluser.name;

SELECT alluser.name FROM alluser JOIN alltask ON alluser.id = alltask.name JOIN logs ON alltask.id = logs.taskname GROUP BY alluser.name HAVING SUM(logs.logtime)>=100;