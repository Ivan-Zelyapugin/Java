/**
 * Класс User представляет сущность для хранения данных о пользователе.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.models;

import com.laba.SN.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@EntityScan
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    /**
     * Идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    /**
     * Электронная почта пользователя (уникальная).
     */
    @Column(name="email", unique = true)
    private String email;

    /**
     * Имя пользователя.
     */
    @Column(name="first_name")
    private String first_name;

    /**
     * Фамилия пользователя.
     */
    @Column(name="second_name")
    private String second_name;

    /**
     * Дата рождения пользователя.
     */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="birthday")
    private Date birthday;

    /**
     * Пароль пользователя.
     */
    @Column(name="password", length = 1000)
    private String password;

    /**
     * Флаг активности пользователя.
     */
    @Column(name="active")
    private boolean active;

    /**
     * Флаг статуса пользователя.
     */
    @Column(name="status")
    private boolean status;

    /**
     * Роли пользователя.
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name="roles")
    private Set<Role> roles = new HashSet<>();

    /**
     * Аватар пользователя.
     */
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private Avatar avatar;

    /**
     * Список постов пользователя.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    /**
     * Идентификатор аватара пользователя.
     */
    private Long avatarId;

    /**
     * Дата создания пользователя.
     */
    @Column(name="date_of_created")
    private LocalDateTime dateOfCreated;

    /**
     * Инициализация данных перед сохранением в базу данных.
     */
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
        active = true; // Установите активность пользователя по умолчанию
    }

    /**
     * Получение списка уникальных постов пользователя.
     *
     * @return Список уникальных постов пользователя.
     */
    public List<Post> getPosts(){
        Set<Post> uniquePosts = new HashSet<>(this.posts);
        return new ArrayList<>(uniquePosts);
    }

    /**
     * Добавление аватара к пользователю.
     *
     * @param avatar Аватар для добавления.
     */
    public void addAvatarToUser(Avatar avatar){
        avatar.setUser(this);
        this.avatar = avatar;
    }

    /**
     * Проверка, является ли пользователь администратором.
     *
     * @return true, если пользователь является администратором, в противном случае - false.
     */
    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }

    /**
     * Проверка, является ли пользователь модератором.
     *
     * @return true, если пользователь является модератором, в противном случае - false.
     */
    public boolean isModer(){
        return roles.contains(Role.ROLE_MODER);
    }

    /**
     * Получение списка ролей пользователя для Spring Security.
     *
     * @return Список ролей пользователя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    /**
     * Получение пароля пользователя для Spring Security.
     *
     * @return Пароль пользователя.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Получение имени пользователя для Spring Security.
     *
     * @return Имя пользователя.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Проверка, активен ли аккаунт пользователя для Spring Security.
     *
     * @return true, если аккаунт активен, в противном случае - false.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверка, не заблокирован ли аккаунт пользователя для Spring Security.
     *
     * @return true, если аккаунт не заблокирован, в противном случае - false.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверка, не истек ли срок действия учетных данных пользователя для Spring Security.
     *
     * @return true, если срок действия учетных данных не истек, в противном случае - false.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверка, включен ли аккаунт пользователя для Spring Security.
     *
     * @return true, если аккаунт включен, в противном случае - false.
     */
    @Override
    public boolean isEnabled() {
        return active;
    }
}
