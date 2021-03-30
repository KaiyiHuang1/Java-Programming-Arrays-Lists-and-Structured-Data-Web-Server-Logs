
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        analyzer.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer analyzerUnique = new LogAnalyzer();
        analyzerUnique.readFile("weblog2_log");
        analyzerUnique.printAll();
        int unique=analyzerUnique.countUniqueIPs();
        System.out.println("Number of unique IP: " + unique);
        analyzerUnique.printAllHigherThanNum(400);
        ArrayList<String> UniqueDayIP = analyzerUnique.uniqueIPVisitsOnDay ("Mar 17");
        System.out.println("Number of unique IP on Mar 17: " + UniqueDayIP.size());
        int count = analyzerUnique.countUniqueIPsInRange(400, 499);
        System.out.println("Number of unique IP that has statusCode between 200 and 299: " + count);
    }
    
    public void testCounts(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        System.out.println("The most # visit: "+la.mostNumberVisitsByIP(counts));
        ArrayList ips = la.iPsMostVisits(counts);
        System.out.println("The most # visit ips: "+ips);
        HashMap<String,ArrayList<String>> ipday = la.iPsForDays();
        System.out.println(ipday);
        System.out.println("The day with most # visit ips: "+la.dayWithMostIPVisits(ipday));
        System.out.println("The most visited ips on Sep 30: "+la.iPsWithMostVisitsOnDay(ipday, "Sep 30"));
    }
    
}
