package com.bprocess.batchscheduler.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_queue")
public class TaskQueueEntity {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  // private long id;
  private String content;
  private String status;
  private String totalEntries;
  private String pendingEntries;
  private String completedEntries;
  private long ownerId;
  private String createdAt;
  private String updatedAt;
  private String finishedAt;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getTotalEntries() {
    return totalEntries;
  }

  public void setTotalEntries(String totalEntries) {
    this.totalEntries = totalEntries;
  }


  public String getPendingEntries() {
    return pendingEntries;
  }

  public void setPendingEntries(String pendingEntries) {
    this.pendingEntries = pendingEntries;
  }


  public String getCompletedEntries() {
    return completedEntries;
  }

  public void setCompletedEntries(String completedEntries) {
    this.completedEntries = completedEntries;
  }


  public long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }


  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }


  public String getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(String finishedAt) {
    this.finishedAt = finishedAt;
  }

}
