## Sealed Class
+ sealed 클래스는 자기 자신이 추상 클래스이고 , 자신을 상속받는 여러 서브 클래스들을 가질 수 있음
+ 상속을 지원하기 때문에 상속을 활용한 풍부한 동작 구현 가능
+ 자신을 상속받는 서브 클래스의 종류를 제한할 수 있다.
    - sealed 클래스의 서브 클래스들은 반드시 같은 파일 내에 선언되어야 함(sealed클래스의 서브클래스를 상속한 클래스들은 같은파일 내에 없어도 됨)
    - sealed 클래스는 기본적으로 추상클래스임
    - sealed 클래스는 private 생성자만 가짐
    
    