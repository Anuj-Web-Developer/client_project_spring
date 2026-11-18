package com.billing.attachment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.billing.attachment.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer>{

}
