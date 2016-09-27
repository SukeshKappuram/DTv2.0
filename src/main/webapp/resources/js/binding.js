var app=angular.module('MyApp',[]);
app.controller("mainCtrl",function($scope)
{
	$scope.data={
			label:"MyButton"
	};
});

app.directive('interactiveBtn',function(){
	return{
		restrict:'A',
		link: function(scope,element,attrs){
			element.bind('mouseenter',function(){
				console.log(element);
				element[0].innerText="Rolled Over";
			});
			
			element.bind('mouseleave',function(){
				console.log(element);
				element[0].innerText="Rolled Out";
			});
		}
	}
})

.directive('somedata',function(){
	return{
		restrict:'E',
		transclude:true,
		template:'<h1> Hello World !</h1>'
	}
});