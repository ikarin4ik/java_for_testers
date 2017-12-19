package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;



public class TestBase {


    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    private Issue issue;

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        ObjectRef issueDataStatus = issueData.getStatus();
        return "new".equals(issueDataStatus.getName());


    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("mantis.connectUrl")));
    }

    
}
