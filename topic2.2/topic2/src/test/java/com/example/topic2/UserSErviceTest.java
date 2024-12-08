package com.example.topic2;

import com.example.topic2.model.User;
import com.example.topic2.repository.UserRepository;
import com.example.topic2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class UserSErviceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Tesztfelhasználó inicializálása
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setPassword("password123");
    }

    @Test
    public void testSaveUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User savedUser = userService.saveUser(testUser);

        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getUsername());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    public void testFindByUsername() {
        when(userRepository.findByUsername("testUser")).thenReturn(java.util.Optional.of(testUser));

        User foundUser = userService.findByUsername("testUser");

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    public void testIsLoggedIn_WhenLoggedIn() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);

        assertTrue(userService.isLoggedIn());
    }

    @Test
    public void testIsLoggedIn_WhenNotLoggedIn() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);

        assertFalse(userService.isLoggedIn());
    }

    @Test
    public void testGetCurrentUserByName() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser");
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);

        String username = userService.getCurrentUserByName();

        assertEquals("testUser", username);
    }



    @Test
    public void testLoadUserByUsername_ThrowsException() {
        when(userRepository.findByUsername("nonexistentUser")).thenReturn(java.util.Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonexistentUser");
        });
    }


}

