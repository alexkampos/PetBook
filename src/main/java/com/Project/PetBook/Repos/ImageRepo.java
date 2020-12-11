
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ImageRepo extends JpaRepository<Image, Long>{
    

}
