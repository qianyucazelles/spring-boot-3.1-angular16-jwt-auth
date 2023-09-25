USE `backenddb`;
select * from app_user u left join user_roles ur on u.id=ur.user_id left join roles r on r.id=ur.role_id