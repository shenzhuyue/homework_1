package homework_1.homework_1.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @ManyToMany(targetEntity = user.class)
    private List<user> friends=new ArrayList<user>();
}
