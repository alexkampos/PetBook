
package com.Project.PetBook.Pojos;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Mail {
    
    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;
    
}
