package com.sparta.hh99springlv4.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


// 일반적인 RESTful API 에서 사용되는 응답 형식을 표현하는 클래스 ! ! !

// 표준화된 응답 형식
// responseDto 클래스를 사용하면 API 의 응답 형식을 표준화할 수 있다.
// 모든 응답은 status, message, data 세 가지 요소로 구성되며, 이는 많은 RESTful API 에서 사용되는 일반적인 형식
// 이를 통해 클라이언트는 일관된 응답 형식을 기대

// 응답 상태 표현
// status 필드를 통해 응답의 성공 또는 실패 여부를 표현
// 이를 통해 클라이언트는 서버의 응답 상태를 쉽게 확인
//
// 응답 메시지 제공
// message 필드를 통해 클라이언트에게 응답에 관한 추가 정보를 제공
// 이를 통해 클라이언트는 서버로부터 받은 응답을 이해하고 적절히 처리
//
// 응답 데이터 전달
// data 필드를 통해 클라이언트에게 요청에 대한 실제 데이터를 전달
// 이는 API 요청에 대한 결과나 추가 정보를 클라이언트에게 제공하는 데 사용
//
// 따라서 ResponseDto 클래스는 API의 일관된 응답 형식을 제공하고 클라이언트와의 통신을 효율적으로 관리하기 위해 사용됩니다.

@AllArgsConstructor
@Getter
public class ResponseDto<T> {

    private boolean status;
    private String message;
    private T data;

    // public 사용, static 사용
    // <T> 제네릭 타입 매개변수 :  다양한 타입의 객체를 다루는 메서드나 컬렉션 클래스 등을 작성할 때 사용되는 개념
    // 제네릭을 사용하면 클래스나 메서드를 정의할 때 타입을 파라미터화할 수 있다.
    // <T>는 임의의 타입을 나타내며, 실제 사용될 때 구체적인 타입으로 대체된다.

    // 예를 들면,
    // ResponseDto<T> : ResponseDto 객체를 반환, <T>는 응답의 데이터(data) 필드의 타입을 나타낸다.
    // ResponseDto<String>은 데이터 필드가 문자열 타입인 응답
    // ResponseDto<Integer>은 데이터 필드가 정수 타입인 응답
    public static <T> ResponseDto<T> success(String message, T data) {
        return new ResponseDto<>(true, message, data); // boolean 값, 응답 메시지, 데이터가 매개변수
    }

    public static <T> ResponseDto<T> fail(String message) {
        return new ResponseDto<>(false, message, null);
    }

    public static <T> ResponseDto<T> fail(String message, T data) {
        return new ResponseDto<>(false, message, data);
    }

}
