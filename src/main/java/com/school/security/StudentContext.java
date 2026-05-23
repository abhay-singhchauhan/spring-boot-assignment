package com.school.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class StudentContext {

    public Long getCurrentStudentId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AuthenticatedUser user) {
            if (user.role() == Role.STUDENT && user.studentId() != null) {
                return user.studentId();
            }
        }
        throw new AccessDeniedException("Student authentication required");
    }
}
