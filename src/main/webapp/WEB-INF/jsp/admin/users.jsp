<div id="page-wrapper" ng-app="UserManagementApp">
	<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">User Management</h1>
        </div><!-- /.col-lg-12 -->
    </div>
    
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-bar-chart-o fa-fw"></i> Users
                    <div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                Actions
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li><a href="#">Action</a>
                                </li>
                                <li><a href="#">Another action</a>
                                </li>
                                <li><a href="#">Something else here</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="#">Separated link</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div><!-- /.panel-heading -->
                
                <div class="panel-body" ng-controller="UserManagementController">
                    <table class="table table-bordered table-striped">
                    	<thead>
                    		<tr>
                    			<th></th>
                    			<th>ID</th>
                    			<th>Name</th>
                    			<th>Surname</th>
                    			<th>Email</th>
                    			<th>Username</th>
                    		</tr>
                    	</thead>
                    	<tr ng-repeat="user in users">
                    		<td>
                    			<a href="#/edit/{{user.id}}">Edit</a>
                    		</td>
                    		<td>{{user.id}}</td>
                    		<td>{{user.name}}</td>
                    		<td>{{user.surname}}</td>
                    		<td>{{user.email}}</td>
                    		<td>{{user.username}}</td>
                    	</tr>
                    </table>
                </div><!-- /.panel-body -->
            </div>
		</div>
	</div>
	
	<script type="text/ng-template" id="modalContainer">
    </script>
    
    <div ng-view=""></div>
</div>