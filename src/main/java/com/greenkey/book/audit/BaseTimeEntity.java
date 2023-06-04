package com.greenkey.book.audit;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 2023.5.27(토) 15h55

/**
 * 모든 Entity의 상위 클래스 + Entity의 생성/수정일자 자동 관리
 */
@Getter
@MappedSuperclass // 이 클래스를 상속할 때 필드들도 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class) // Auditing 기능(나의 질문 = definition? todo) 포함
public abstract class BaseTimeEntity {
    @CreatedDate // entity 생성 및 저장되는 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // entity 값 변경 시간 자동 저장
    private LocalDateTime modifiedDate;
}
