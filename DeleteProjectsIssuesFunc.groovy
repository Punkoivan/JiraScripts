import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.project.ProjectManager
import org.apache.log4j.Logger
import org.apache.log4j.Level

def void DeleteIssuesByProject(projectKey, boolean debug=false, boolean resetCounter=true) {
    '''
    Method return a List with all issues id
    in in given project(%project)
    Parameter: 
        String projectKey (not case sensivity)
        boolean debug - turn on debug, by dedault toruned of
        boolean resetCounter - reset issue counter or not, by default is true
    '''
    Logger logger = Logger.getLogger("")
    logger.setLevel(Level.INFO)
    if (debug) {
        logger.setLevel(Level.DEBUG)
    }
    ProjectManager projectManager = ComponentAccessor.getProjectManager()
    def project = projectManager.getProjectByCurrentKeyIgnoreCase(projectKey)
    try {
        projectManager.removeProjectIssues(project)
        logger.debug("Issue for the ${project} has been deleted}")
        if (resetCounter) {
            projectManager.setCurrentCounterForProject(project, 0)
        }
    }
    catch(e) {
        logger.debug(e)
    {
    }


def main(boolean debug=false, resetCounter=true) {
    DeleteIssuesByProject(projectKey, debug)
}


main()
