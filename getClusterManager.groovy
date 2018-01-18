import com.atlassian.jira.cluster.DefaultClusterManager 
import com.atlassian.jira.cluster.DefaultClusterNodes 
import com.atlassian.jira.cluster.ClusterNodePropertiesImpl 
import com.atlassian.jira.startup.JiraHomeLocator.SystemJiraHome 
import com.atlassian.jira.cluster.OfBizClusterNodeStore 
import com.atlassian.jira.ofbiz.DefaultOfBizDelegator
import com.atlassian.jira.util.BuildUtilsInfoImpl  
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.license.ClusterLicenseCheckImpl 
import com.atlassian.jira.cluster.OfBizMessageHandlerService 
import com.atlassian.jira.cluster.OfBizClusterMessageStore 
import com.atlassian.event.internal.EventPublisherImpl
import com.atlassian.event.internal.AsynchronousAbleEventDispatcher
import com.atlassian.event.internal.EventExecutorFactoryImpl
import com.atlassian.event.internal.EventThreadPoolConfigurationImpl
import com.atlassian.event.internal.ListenerHandlerConfigurationImpl


def getClusterManager() {
    def eventThreadPool = new EventThreadPoolConfigurationImpl()
    def eventExecutor = new EventExecutorFactoryImpl(eventThreadPool)
    def eventDispatcher = new AsynchronousAbleEventDispatcher(eventExecutor)
    def listenerDispatcher = new ListenerHandlerConfigurationImpl()
    def eventPublisher = new EventPublisherImpl(eventDispatcher, listenerDispatcher)
    def clusterLicense = new ClusterLicenseCheckImpl()
    def ofBizDelegator = ComponentAccessor.getOfBizDelegator() 
    def clusterMessageStore = new OfBizClusterMessageStore(ofBizDelegator)
    def jiraHome = new SystemJiraHome()
    def clusterProperties = new ClusterNodePropertiesImpl(jiraHome)
    def clusterNodeStore = new OfBizClusterNodeStore(ofBizDelegator)
    def buildUtils = new BuildUtilsInfoImpl()
    def clusterNodes = new DefaultClusterNodes(clusterProperties, clusterNodeStore, buildUtils)
    def messageHandler = new OfBizMessageHandlerService(clusterNodes, clusterMessageStore, eventPublisher)
    def clusterManager = new DefaultClusterManager(clusterNodes, eventPublisher, clusterLicense, messageHandler)
    return clusterManager
}
