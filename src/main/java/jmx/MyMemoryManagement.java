package jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class MyMemoryManagement {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        // 获取 MBean Server
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        MyMemory myMemory = new MyMemory();
        myMemory.setTotal(100L);
        myMemory.setUsed(20L);
        // 注册
        ObjectName objectName = new ObjectName("cn.monkeykong.jmx:type=myMemory");
        platformMBeanServer.registerMBean(myMemory, objectName);
        while (true) {
            Thread.sleep(3000);
            System.out.println(myMemory.doMemoryInfo());
        }
    }
}
