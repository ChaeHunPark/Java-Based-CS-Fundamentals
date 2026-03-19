# 🛠️ Java-Based CS Fundamentals
> **"이론을 코드로 증명하며 채워가는 나만의 엔지니어링 노트"**

이 저장소는 CS 기초 지식을 Java 실습을 통해 깊이 있게 탐구합니다. 단순한 지식 습득을 넘어, **'왜(Why)'**와 **'어떻게(How)'**를 코드로 증명하는 과정을 기록하며, 대체 불가능한 백엔드 엔지니어로 성장하는 여정을 담습니다.

---

## 🎯 학습 철학 (Philosophy)
1. **First Principles:** 모든 기술의 근간이 되는 OS와 아키텍처 원리에 집중한다.
2. **Evidence-Based:** 모호한 이론은 Java 코드로 직접 구현하여 동작을 증명한다.
3. **Refactoring Mindset:** 단순 기능을 넘어 **OCP, 전략 패턴** 등 유지보수 가능한 설계를 지향한다.
4. **Deep Dive:** "돌아만 가는 코드"에 만족하지 않고, 내부 동작 원리를 끝까지 파헤친다.

---

## 🧱 탐구 카테고리 (Categories)

### 📂 [Operating System](./OS) `✅ Deep Dive`
- **핵심:** 프로세스, 스레드, 동기화, 데드락, 메모리 관리
- **증명:** `synchronized`를 통한 Race Condition 해결, Deadlock 발생 조건 재현, OOM(OutOfMemory) 상황 관찰 완료
- [상세 내용 보기 👉](./OS/src/README.md)

### 📂 [Architecture](./Architecture) `🔥 Learning`
- **핵심:** 디자인 패턴 및 객체지향 설계 원칙(SOLID)
- **실습:** `Strategy Pattern`을 활용한 복합 액션 시스템 및 'Tell, Don't Ask' 원칙 기반 리팩토링
- [상세 내용 보기 👉](./Architecture/src/README.md)

### 📂 [Network](./Network) `🏗️ Planned`
- **핵심:** TCP/IP 4계층, HTTP/HTTPS 프로토콜 및 패킷 흐름 분석
- **목표:** 소켓 프로그래밍을 통한 네트워크 통신의 근본적 이해

### 📂 [Database](./Database) `🏗️ Planned`
- **핵심:** 데이터 저장 구조(B-Tree), Index 최적화, Transaction 격리 수준
- **목표:** 대규모 트래픽에서도 데이터 무결성을 보장하는 설계 능력 배양

---

## 🔗 외부 저장소 (External Links)
- 📂 [Data Structure & Algorithm (Go to Repo 🔗)](https://github.com/ChaeHunPark/Java-DataStructure-Archive)
  - 자료구조 구현 및 알고리즘 문제 풀이는 별도 저장소에서 관리합니다.

---

## 📈 최근 학습 기록 (Logs)
- **[2026-03-20]** 🏗️ **Architecture:** Step 02~03 (Factory Method, Strategy) 완료. 유연한 무기/이동 시스템 설계 및 리팩토링.
- **[2026-03-20]** 🍎 **OS:** Step 04~06 완료. Race Condition 해결 전략 및 데드락 발생 조건 실습.
- **[2026-03-02]** 🍎 **OS:** Step 01~03 (메모리, 스레드, 스케줄링) 원리 파악 및 실습 완료.

---

### 💬 엔지니어링 노트
> "비행기 위에서는 공격할 수 없다"는 로직을 짤 때 `instanceof` 대신 인터페이스에 상태를 묻는 방식을 고민합니다. 또한 멀티스레드 환경에서 데이터가 꼬이는 원인을 OS 수준에서 분석하고 `synchronized`로 제어해 봅니다. 이런 깊은 탐구가 단단한 아키텍처의 시작이라 믿습니다.