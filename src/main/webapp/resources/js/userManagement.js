var app = angular.module("UserManagementApp", ['ngRoute', 'ui.bootstrap']);

app.config(['$routeProvider', function ($routeProvider) {
	$routeProvider.when('/edit/:userId', {
		templateUrl: 'modalContainer',
		controller: 'EditUserController'
	}).when('/view', {
		templateUrl: '/static/ngtemplates/editUser.html',
		controller: 'EditUserController'
	});
}]);

app.factory("userService", function ($http, $q) {
	var userService = {
		getUsers: function (firstItem, numberOfItems) {
			var deferred = $q.defer();
			
			$http({
				method: 'GET',
				url: '/admin/users/getUsers',
				params: {
					firstResult: firstItem,
					maxResults: numberOfItems
				}
			}).success(function (data, status, headers, config) {
				deferred.resolve(data.users);
			}).error(function (data, status, headers, config) {
				deferred.reject(data);
			});
			
			return deferred.promise;
		}
	};
	
	return userService;
});

app.controller("UserManagementController", ['$scope', 'userService', function ($scope, userService) {
	$scope.users = [];
	
	var promise = userService.getUsers(0, 10);
	promise.then(function (users) {
		$scope.users = users;
	});
}]);

app.controller("EditUserController", ['$scope', '$routeParams', 'userService', '$modal', function ($scope, $routeParams, userService, $modal) {
	$scope.user = null;
	$scope.userId = $routeParams.userId;
	
	var promise = userService.getUsers(0, 10);
	promise.then(function (users) {
		angular.forEach(users, function (user, key) {
			if (user.id == $scope.userId) {
				showUser(user);
				return false;
			}
		});
	});
	
	function showUser(user) {
		$scope.user = user;
		var modalInstance = $modal.open({
			animation: true,
			templateUrl: '/static/ngtemplates/editUser.html',
			controller: 'ModalInstanceCtrl',
			resolve: {
				user: function () {
					return $scope.user;
				}
			}
		});
		
		modalInstance.result.then(function () {
			window.location.href = '#';
		}, function () {
			window.location.href = '#';
		});
	}
}]);

app.controller('ModalInstanceCtrl', function ($scope, $modalInstance, $route, user) {
	$scope.user = user;

	$scope.ok = function () {
		$modalInstance.close();
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});