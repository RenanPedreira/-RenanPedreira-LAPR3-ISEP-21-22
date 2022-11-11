package lapr.project.auth.mappers;

import lapr.project.auth.domain.model.*;
import lapr.project.auth.mappers.dto.UserRoleDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class UserRoleMapper {

    /**
     * Method that returns a new UserRoleDTO by using an user role
     * @param role
     * @return a new UserRoleDTO by using an user role
     */
    public UserRoleDTO toDTO(UserRole role)
    {
        return new UserRoleDTO(role.getId(),role.getDescription());
    }

    /**
     * Method that returns a list of user role dto by using a list of roles
     * @param roles
     * @return a list of user role dto by using a list of roles
     */
    public List<UserRoleDTO> toDTO(List<UserRole> roles)
    {
        List<UserRoleDTO> rolesDTO = new ArrayList<>();
        for(UserRole role:roles)
        {
            rolesDTO.add(this.toDTO(role));
        }
        return rolesDTO;
    }

}
