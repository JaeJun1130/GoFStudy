# GoFStudy
GoF 디자인 패턴 공부하기

## [X] 싱글톤 패턴 
- 싱글톤 패턴을 구현하는 방법
  - 늦은 초기화 (Thread-Unsafe) -> private static 
- 싱글톤 패턴을 Thread Safe 하게 구현하는 방법
  - 동기화(synchronized)
  - 이른 초기화(eager initialization) -> static 메소드
  - double checked locking (volatile 사용함으로 thread safe)
  - static inner class (LazyHolder)
- 싱글톤 패턴을 깨트리는 방법
  - 리플렉션 
  - 직렬화, 역직렬화 (역직렬화 동작시 readResolve를 구현해 싱글톤을 보장할 수 있음)
- 싱글톤 패턴 안전하고 단순하게 구현하는 방법
  - enum class 사용
  - 리플렉션 사용불가
  - 직렬화, 역직렬화 안전
  - enum은 상속 불가능
  - class 로딩 시점에 미리 만들어짐
- 자바와 스프링에서 찾아보는 싱글톤 패턴
    - Runtime.class 싱글턴 패턴을 사용한 class
    - spring Bean 등록 (ApplicationContext)
