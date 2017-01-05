import odelia.gina.actuator.*

class BootStrap {

    def init = { servletContext ->
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN')
            .save()
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER')
            .save()    

        if (!User.findByUsername('admin')) {
            def admin = new User(username: 'admin', enabled: true, title: 'Mr', firstName: 'Bertrand', lastName: 'Goetzmann', password: 'password', email: 'bgoetzmann@odelia-technologies.com')
            admin.save(failOnError: true, flush: true)  
            UserRole.create admin, adminRole, true
        }

        environments {
            development {
                // Create a user using ROLE_USER role
                def user = new User(username: 'ngoetzmann', title: 'Mr', firstName: 'Nathanael', lastName: 'Goetzmann', password: 'password', email: 'ngoetzmann@odelia-technologies.com')
                user.save(failOnError: true, flush: true)
                UserRole.create user, userRole, true              
            }
        }
    }

    def destroy = {
    }
}
