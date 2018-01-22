import org.ofbiz.core.entity.GenericValue
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.bc.issue.IssueService
import com.atlassian.jira.project.ProjectManager
import org.apache.log4j.Logger
import org.apache.log4j.Level

def getIssueByProject(projectKey, boolean debug=false, boolean resetCounter=true) {
    '''
    Method return a List with all issues id
    in in given project(%project)
    Parameter: 
        String projectKey (not case sensivity)
        boolean debug - turn on debug, by dedault toruned of
        boolean resetCounter - reset issue counter or not, by default is true
    Return: List of string issues
    '''
    Logger logger = Logger.getLogger("")
    logger.setLevel(Level.INFO)
    if (debug) {
        logger.setLevel(Level.DEBUG)
    }
    def project = projectManager.getProjectByCurrentKeyIgnoreCase(projectKey)
    try {
        removeProjectIssues(project)
        logger.debug("Issue for the ${project has been deleted}")
    {
    catch(e) {
        logger.debug(e)
    {
    }


def main(boolean debug=false, resetCounter=true) {
    DeleteIssuesByProject(projectKey, debug)
}


main()
