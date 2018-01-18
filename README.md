# JiraScripts
Repository contains ocripts for Jira writing in groovy language.
Tested with Jira versions 7.x, scripts were ran with Adaptavist ScriptRunner add-on.
Might be run as validators/postfunctions/etc.

 ## Scripts description
**DeleteAllIssues**

Script does exactly what it name says - delete all issues in Jira.

For succeful execution you must run this one with user that have delete issue
permission in _all_ projects. The easiest way to do this is to change Permission schemes to allow runuser Delete issue [check in Atlassian documentation](https://confluence.atlassian.com/adminjiraserver075/managing-project-permissions-935391141.html)

By default script doesn't run in debug mode, to run it in debug please set _"debug"_ flag to true in 
_'main'_ method.
In debug mode script writes to a standard log file (%JIR\_HOME/logs/catalina.out in Linux OS):
* in case of successful deleting:
  "issue %ISSUE-ID has been deleted"
* in case of failure:
  "There is an error while deleting %ISSUE-ID - User must have Delete issue permission in project!"

Be aware! Log is created for every issue - on a really large instance might be root cause of freezing!

**DeleteProjectIssues**

Almost the same as DeleteAllIssues

Script does exactly what it name says - delete issues for given project in Jira.
For succeful execution you must run this one with user that have delete issue
permission in _all_ projects. The easiest way to do this is to change Permission schemes to allow runuser Delete issue [check in Atlassian documentation](https://confluence.atlassian.com/adminjiraserver075/managing-project-permissions-935391141.html)

By default script doesn't run in debug mode, to run it in debug please set _"debug"_ flag to true in 
_'main'_ method.
In debug mode script writes to a standard log file (%JIR\_HOME/logs/catalina.out in Linux OS):
* in case of successful deleting:
  "issue %ISSUE-ID has been deleted"
* in case of failure:
  "There is an error while deleting %ISSUE-ID - User must have Delete issue permission in project!"

Be aware! Log entry is created for every issue - on a really large instance might be root cause of freezing!
**getHeap**
A small script that allows you to get useful information about heap current usage.
Usecases:
1. Set up a scheduled tasks and add notification about high usage via mail or get ticket automatically created in queue which responsible for Jira instance.
2. Set up a custom REST Endpoint and return only usage. Here you go with monitoring system - just fetch data from URL with Nagios/Zabbix/Crontab/etc and set yours tresholds.
Please note that for DataCenter edition you should set different endpoints - I did *not* test it but should works.
Bonus - you don't need JDK for this!
**getClusterManager**
Returns ClusterManager instance. [Atlassian Docs](https://docs.atlassian.com/software/jira/docs/api/7.5.0/com/atlassian/jira/cluster/DefaultClusterManager.html)
Allows us to fetch info about each nodes in cluster.
