package org.example.persist;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@DynamicInsert
@DynamicUpdate
@Entity(name = "TASK")
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(value=EnumType.STRING)
    private TaskStatus status;


    private Date dueDate;  // 추가된 필드: 마감일 (dueDate)

    @CreationTimestamp
    @Column(insertable = false ,updatable=false)
    private Timestamp createdAt;


    @UpdateTimestamp
    @Column(insertable = false ,updatable=false)
    private Timestamp updatedAt;  // 추가된 필드: 수정일

    // 생성 시 자동으로 createdAt 값을 설정
}