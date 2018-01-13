# JiraScripts
Repository contains ocripts for Jira writing in groovy language.
Tested with Jira versions 7.x, scripts were ran with Adaptavist ScriptRunner add-on.
Might be run as validators/postfunctions/etc.

 ## Scripts description
**DeleteAllIssue**
Script does exactly waht it name says - delete all issue in Jira.
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
