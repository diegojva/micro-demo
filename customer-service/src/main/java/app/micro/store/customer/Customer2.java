package app.micro.store.customer;

import app.micro.store.customer.entities.Region;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Customer2 {

    private String photoUrl;


    private Region region;

    private String state;

}
