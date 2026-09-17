package com.kite.libai.core.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class TextUtils {

    /**
     * URL 提取正则表达式
     */
    private static final Pattern URL_PATTERN = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?)://|www\\.)"
                    + "(([\\w\\-]+\\.)+?([\\w\\-.~]+/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    /**
     * 话题 提取正则表达式
     */
    private static final Pattern TOPIC_PATTERN =
            Pattern.compile("#[^#]+#");

    /**
     * @ xxx 提取正则表达式
     */
    private static final Pattern USER_MENTION_PATTERN =
            Pattern.compile("@[\u4e00-\u9fa5a-zA-Z0-9_-]{2,30}");


    /**
     * 删除文本中 url
     *
     * @param text 原文本
     * @return 处理后的文本
     */
    public static String removeUrls(String text) {
        Matcher matcher;
        String newTweet = text.trim();
        String cleanedText = "";
        while (!newTweet.equals(cleanedText)) {
            cleanedText = newTweet;
            matcher = URL_PATTERN.matcher(cleanedText);
            newTweet = matcher.replaceAll("");
            newTweet = newTweet.trim();
        }
        return cleanedText;
    }

    /**
     * 提取文本中 url
     *
     * @param text text
     * @return 文本中的 url
     */
    public static List<String> getUrls(String text) {
        List<String> urls = new ArrayList<>();
        Matcher matcher = URL_PATTERN.matcher(text);
        while (matcher.find()) {
            int matchStart = matcher.start(1);
            int matchEnd = matcher.end();
            String tmpUrl = text.substring(matchStart, matchEnd);
            urls.add(tmpUrl);
            text = text.replace(tmpUrl, "");
            matcher = URL_PATTERN.matcher(text);
        }
        return urls;
    }

    /**
     * 删除文本中话题
     *
     * @param text 原文本
     * @return 处理后的文本
     */
    public static String removeTopics(String text) {
        Matcher matcher;
        String newTweet = text.trim();
        String cleanedText = "";
        while (!newTweet.equals(cleanedText)) {
            cleanedText = newTweet;
            matcher = TOPIC_PATTERN.matcher(cleanedText);
            newTweet = matcher.replaceAll("");
            newTweet = newTweet.trim();
        }
        return cleanedText;
    }

    /**
     * 提取文本中话题
     *
     * @param text text
     * @return 文本中的话题
     */
    public static List<String> getTopics(String text) {
        List<String> topics = new ArrayList<>();
        Matcher matcher = TOPIC_PATTERN.matcher(text);
        while (matcher.find()) {
            int matchStart = matcher.start();
            int matchEnd = matcher.end();
            String tempTopic = text.substring(matchStart, matchEnd);
            topics.add(tempTopic);
            text = text.replace(tempTopic, "");
            matcher = TOPIC_PATTERN.matcher(text);
        }
        return topics;
    }

    /**
     * 删除文本中@到的用户
     *
     * @param text 原文本
     * @return 处理后的文本
     */
    public static String removeUserMentions(String text) {
        Matcher matcher;
        String newTweet = text.trim();
        String cleanedText = "";
        while (!newTweet.equals(cleanedText)) {
            cleanedText = newTweet;
            matcher = USER_MENTION_PATTERN.matcher(cleanedText);
            newTweet = matcher.replaceAll("");
            newTweet = newTweet.trim();
        }
        return cleanedText;
    }

    /**
     * 提取文本中的@的用户
     *
     * @param text text
     * @return 文本中的 @的用户
     */
    public static List<String> getUserMentions(String text) {
        List<String> userMentions = new ArrayList<>();
        Matcher matcher = USER_MENTION_PATTERN.matcher(text);
        while (matcher.find()) {
            int matchStart = matcher.start();
            int matchEnd = matcher.end();
            String tmpUserMention = text.substring(matchStart, matchEnd);
            userMentions.add(tmpUserMention);
            text = text.replace(tmpUserMention, "");
            matcher = USER_MENTION_PATTERN.matcher(text);
        }
        return userMentions;
    }
}
