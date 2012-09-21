package de.javamagazin.activiti.spring.process;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.test.Deployment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.javamagazin.activiti.spring.domain.CustomerFacade;
import de.javamagazin.activiti.spring.domain.model.Customer;
import de.javamagazin.activiti.spring.persistence.OrderAttribute;
import de.javamagazin.activiti.spring.persistence.OrderType;
import de.javamagazin.activiti.spring.process.service.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:process-context.xml")
@ActiveProfiles("standalone")
@Deployment(resources = { "process/purchase.bpmn" })
public class SimplePurchaseTest {
    
    private static final Log LOG = LogFactory.getLog(SimplePurchaseTest.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private CustomerFacade customerFacade;
    
    @Autowired
    private PurchaseService purchaseService;

    @Before
    public void printSomeDebugInformation() {
        LOG.info("About to print some debug information");
        for (ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().list()) {
            LOG.info("Found the process with the id: " + processDefinition.getId());
        }
    }

    @Test
    public void testWithActivitiPersistence() {
        Customer customer = customerFacade.loadCustomer(1L);

        Map<String, Object> processVariables = new HashMap<String, Object>();
        processVariables.put(OrderAttribute.CUSTOMER.name(), customer);
        processVariables.put(OrderAttribute.PRODUCT_ID.name(), 1L);

        runtimeService.startProcessInstanceByKey(OrderType.PURCHASE.getProcessName(), processVariables);
        Assert.assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }
    
    @Test
    public void testWithCustomPersistence() {
        Long orderId = purchaseService.execute(1L, 1L);
        
        Assert.assertTrue(orderId != null);
        Assert.assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }

}