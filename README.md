# Discord Football Notifier Bot

매일 아침 10시, EPL 경기가 있으면 **EPL 경기 일정**, **순위**를 Discord 채널에 자동으로 공지해주는 봇입니다.

---

## 🚀 Features
1. **🔔 오늘의 EPL 경기 일정 자동 전송**
    - 매일 오전 지정된 시간(예: 10:00)에 발송
    - football-data.org API 기반 최신 Matches 조회
    - 경기 일정 UTC → 한국 시간(KST) 자동 변환

2. **🏆 EPL 순위표 자동 전송**
   - 매일 오전 지정된 시간(예: 10:00)에 발송
   - football-data.org API 기반 최신 standings 조회 
   - 팀 순위 승/무/패 등 시각화

## 📦 Tech Stack
| Layer           | Tech                     |
| --------------- | ------------------------ |
| Language        | Java 21                  |
| Framework       | Spring Boot 3            |
| Discord         | JDA 5                    |
| HTTP Client     | Spring WebClient         |
| Scheduler       | Spring Scheduler (cron)  |
| Hosting         | AWS EC2 (Amazon Linux)   |
| Build           | Gradle                   |
