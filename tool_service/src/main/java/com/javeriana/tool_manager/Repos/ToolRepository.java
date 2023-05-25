package com.javeriana.tool_manager.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javeriana.tool_manager.Entities.Tool;

public interface ToolRepository extends JpaRepository<Tool, Long>
{
    
}
