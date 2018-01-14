import org.ofbiz.core.entity.GenericValue
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.project.Project
import com.atlassian.jira.project.ProjectManager
import com.atlassian.jira.bc.issue.IssueService
import com.atlassian.jira.component.ComponentAccessor
import org.apache.log4j.Logger
import org.apache.log4j.Level

def getAllProjects() {
    '''
    Method return a List with all projects that
    existing in Jira instance.
    Return: List of objects, class ProjectImpl
    '''
    def projects = []
    ProjectManager projectManager = ComponentAccessor.getProjectManager()
    projects = projectManager.getProjectObjects()
    return projects
}

def getIssueByProject(project) {
    '''
    Method return a List with all issues id
    in in given project(%project)
    Parameter: 
        project (Class ProjectObject)
    Return: List of string issues
    '''
    def issues = []
    IssueManager issueManager=ComponentAccessor.getIssueManager()
        for (GenericValue issueValue: issueManager.getProjectIssues(project.genericValue)) {
            Issue issue= issueManager.getIssueObject(issueValue.id)
            issues.add issue
        }
        return issues
    }

def void DeleteIssues(List issues, String user, boolean debug=false) {
    '''
    Method try to delete all issues (%issues).
    If there are any errors while deleting an issue 
    you should be able to see error in log file.
    The most common error is a lack of permission.
    User that execute script must have 'Delete issue' permission.
    Parameters: 
        List of issues id (%issues),
        String user with 'Delete issue' permission (%user)
        Boolean debug false by default, set true for debug mode.
    '''
    Logger logger = Logger.getLogger("")
    logger.setLevel(Level.INFO)
    if (debug) {
        logger.setLevel(Level.DEBUG)
    }
    IssueService issueService = ComponentAccessor.getIssueService()
    def userManager = ComponentAccessor.getUserManager()
    def userDelete = userManager.getUserByName(user)
    IssueManager issuemanager = ComponentAccessor.getIssueManager()

    for (issue_string in issues) {
        logger.debug(issue_string)
        try {
            def issue = issuemanager.getIssueObject(issue_string.toString())
            def issue_id = issue.getId()
            validationResult = issueService.validateDelete(userDelete, issue_id)
            if (validationResult.errorCollection.hasAnyErrors()) {
                def error = validationResult.getErrorCollection()
                logger.debug("There is an error while deleting ${issue_string}  " + error)
            }
            else {
               issueService.delete(userDelete, validationResult)
               logger.debug("issue ${issue_string} has been deleted")
            }
        }
        catch(e) {
            logger.debug(e)
        }

    }
}
    Logger logger = Logger.getLogger("")
    logger.setLevel(Level.DEBUG)

def main(boolean debug=false) {
    for (project in agetAllProjects()) {
        issueToDelete = getIssueByProject(project, debug)
        DeleteIssues(issueToDelete, "YourUsername!", debug)
    }
}


main()
