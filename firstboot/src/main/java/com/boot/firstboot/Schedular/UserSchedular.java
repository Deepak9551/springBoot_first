package com.boot.firstboot.Schedular;

import com.boot.firstboot.Enum.Sentiment;
import com.boot.firstboot.Repository.UserRepoImp;
import com.boot.firstboot.Service.AppCache;
import com.boot.firstboot.Service.EmailService;
import com.boot.firstboot.Service.SentimantAnalysisService;
import com.boot.firstboot.entity.MyData;
import com.boot.firstboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchedular {

    @Autowired
    private EmailService emailService;


    @Autowired
    private UserRepoImp userRepoImp;

    @Autowired
    private SentimantAnalysisService sentimantAnalysisService ;
    @Autowired
    private AppCache appCache;
//    @Scheduled(cron = "0 0 9 ? * THU *")
//    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendMain(){

        List<User> users = userRepoImp.getUserForSa();
        for (User user:users){
            //data base se user ka email or sentiment ke basis par data fetch
            List<MyData> myDataList = user.getMyDataList();

//            List<String> collect =  myDataList.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            List<Sentiment> sentiments =  myDataList.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());

//        String entry = String.join(" ",collect);
//        String sentiment = sentimantAnalysisService.getSentiment(entry);
//        emailService.sendEmail(user.getEmail(),"Sentiment for 7 days " ,sentiment);
            Map<Sentiment , Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments){
                if (sentiment!=null){
                    sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment,Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment !=null){
                emailService.sendEmail(user.getEmail(),"Sentiment for 7 days",mostFrequentSentiment.toString());
            }

        }
    }

//    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppcache(){
        appCache.init();
    }

}
