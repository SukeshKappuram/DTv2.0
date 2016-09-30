app.factory('RegistrationService', ['$http', '$q', function($http, $q){
	console.log("RegistrationService...")
    return {
         
             
            createUser: function(adduser){
            	console.log('createUser UserDetails' + adduser)
            	var jsonObject = angular.toJson(adduser)
            	console.log('toJson:' + jsonObject)
                    return $http.post('http://localhost:8081/chitchat/adduser/', jsonObject)
                            .then(
                                    function(response){
                                    	console.log('createUser response:' +response.data)
                                        return response.data;
                                    	return $location.path('/welcome.html');
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating UserDetails');
                                        return $q.reject(errResponse);
                                    }
                            );
            	}
		} 
}]);