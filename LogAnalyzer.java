
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records= new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String Line: fr.lines()){
             LogEntry currLine = WebLogParser.parseEntry(Line);
             if (! records.contains(currLine)) {
             records.add(currLine);
            }
        }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry L : records){
             String IP = L.getIpAddress();
             if(!uniqueIPs.contains(IP)){
                 uniqueIPs.add(IP);
                }
        }
         return uniqueIPs.size();
        }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry L : records){
             int statusCode = L.getStatusCode();
             if(statusCode>num){
                 System.out.println("LogEntry statuscode higher than "+num+" : " + L.toString());
                }
        }
        }
        
     public ArrayList uniqueIPVisitsOnDay (String someday){
         ArrayList<String> UniqueDayIP = new ArrayList<String>();
         for(LogEntry L : records){
             String LogDate = L.getAccessTime().toString().substring(4,10);
             //System.out.println(LogDate);
             if(LogDate.equals(someday)){
                 UniqueDayIP.add(L.getIpAddress());
                }
            }
            return UniqueDayIP;
        }
        
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> result = new HashMap<String, Integer>();
         for(LogEntry Le : records){
             String ip = Le.getIpAddress();
             if(!result.containsKey(ip)){
                 result.put(ip, 1);
                }else{
                    result.put(ip,result.get(ip)+1);
                }
        }
        return result;
    }
        
     public int countUniqueIPsInRange(int low, int high){
         int count = 0; 
         for(LogEntry L : records){
             int statusCode = L.getStatusCode();
             if(statusCode>=low && statusCode<=high){
                 count++;
                }
        }
        return count;
        }
        
    public int mostNumberVisitsByIP(HashMap<String, Integer> map){
        int max= 0;
        for(Integer v: map.values()){
            int convert = v.intValue();
            if(convert > max){
                max = convert;
            }
        }
        return max;
    } 
    
    public ArrayList iPsMostVisits(HashMap<String, Integer> map2){
        int max = mostNumberVisitsByIP(map2);
        ArrayList<String> ips = new ArrayList<String>();
        for(String ip: map2.keySet()){
            if(map2.get(ip) == max){
                ips.add(ip);
            }
        }
        return ips;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipday = new HashMap<String, ArrayList<String>>();
        for(LogEntry L : records){
             String day = L.getAccessTime().toString().substring(4,10);
             String ip = L. getIpAddress();
             ArrayList<String> ips = new ArrayList<String>();
             if(!ipday.containsKey(day)){
                 ipday.put(day, new ArrayList<String>());
                 ipday.get(day).add(ip);
                }else{
                    ipday.get(day).add(ip);
                }
    }
          return ipday;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipday){
        String dayMost = null;
        int dayscount = 0;
        for(String day : ipday.keySet()){
            if(ipday.get(day).size() > dayscount){
                dayscount = ipday.get(day).size();
                dayMost = day;
            }
        }
        System.out.println("The day with most # visit ips has visits: "+dayscount);
        return dayMost;
    }
    
    public ArrayList iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>>ipday, String day){
        ArrayList<String> IPAddresses = new ArrayList<String>();
        HashMap<String, Integer> IPVisitsOneDay = new HashMap<String, Integer>();
        for(String ipOneDay:ipday.get(day)){
            if(!IPVisitsOneDay.containsKey(ipOneDay)){
                 IPVisitsOneDay.put(ipOneDay, 1);
                }else{
                    IPVisitsOneDay.put(ipOneDay,IPVisitsOneDay.get(ipOneDay)+1);
                }
        }
        IPAddresses = iPsMostVisits(IPVisitsOneDay);
        return IPAddresses;
    }
}
