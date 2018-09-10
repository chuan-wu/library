/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.repository;

import chuan.wu.library.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {

    List<BorrowRecord> findAllByUserId(Integer userId);
}
