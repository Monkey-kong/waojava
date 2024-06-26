package jmx;

import java.lang.management.*;
import java.util.List;
import java.util.stream.Collectors;

public class JavaManagementExtensions {
    public static void main(String[] args) {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        String name = operatingSystemMXBean.getName();
        String version = operatingSystemMXBean.getVersion();
        int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
        double systemLoadAverage = operatingSystemMXBean.getSystemLoadAverage();
        System.out.println(String.format("操作系统：%s,版本：%s,处理器：%d个，负载：%s", name, version, availableProcessors, systemLoadAverage));

        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        String compilationMXBeanName = compilationMXBean.getName();
        System.out.println("编译系统：" + compilationMXBeanName);

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        long max = heapMemoryUsage.getMax();
        long used = heapMemoryUsage.getUsed();
        System.out.println(String.format("使用内存：%dMB/%dMB", used / 1024 / 1024, max / 1024 / 1024));

        List<GarbageCollectorMXBean> gcMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        String gcNames = gcMXBeans.stream()
                .map(MemoryManagerMXBean::getName)
                .collect(Collectors.joining(","));
        System.out.println("垃圾收集器：" + gcNames);
    }
}
