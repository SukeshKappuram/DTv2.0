app.controller('RegisterController', ['$scope', 'RegistrationService', function($scope, RegistrationService) {
	console.log("RegisterController...")
          var self = this;
          self.user={name:'',password:'',phno:'',mail:''};
          self.users=[];
               
         
            
          self.createUser = function(user){
        	  console.log('createUser:' + self.user)
        	  console.log('One name of the user'+self.user.name)
              RegistrationService.createUser(self.user)
                      .then(
                      self.fetchAllUsers, 
                              function(errResponse){
                                   console.error('Error while creating User.');
                              } 
                  );
          };
}]);