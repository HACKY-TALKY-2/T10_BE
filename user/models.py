from django.db import models
from django.contrib.auth.models import AbstractBaseUser, BaseUserManager

class UserManager(BaseUserManager):
	# 필수로 필요한 데이터를 선언
    def create_user(self, user_id,location, password):
        if not user_id:
            raise ValueError("Users must have id")
        if not location:
            raise ValueError("Users must have location")
        user = self.model(
            user_id = user_id,
            location = location
        )

        user.set_password(password) # change user password
        user.save(using=self._db)
        return user 
    # python manage.py createsuperuser 사용 시 해당 함수가 사용됨
    def create_superuser(self, user_id, location,  password=None):
        user = self.create_user(
            user_id = user_id,
            location= location,
            password = password
        )
        user.is_admin = True
        user.save(using=self._db)
        return user


class User(AbstractBaseUser):
    member_key_id = models.IntegerField("멤버번호", primary_key= True, unique= True, auto_created= True)
    user_id = models.CharField("사용자 계정", max_length= 40, unique=True)
    password = models.TextField("비밀번호")
    point = models.IntegerField("포인트", default= 0)
    location = models.CharField("지역", max_length= 100)
    
    # 활성화 여부 (기본값은 True)
    is_active = models.BooleanField(default=True)
    # 관리자 권한 여부 (기본값은 False)
    is_admin = models.BooleanField(default=False)
    is_staff = models.BooleanField(default=False)

    USERNAME_FIELD = 'user_id'
    REQUIRED_FIELDS = ['location']

    objects = UserManager()

    @property
    def is_staff(self):
        return self.is_admin
    
    def __str__(self):
        return f"{self.user_id}"
    
    def has_perm(self, perm, obj=None):
        return True

    def has_module_perms(self, app_label):
        return True
    
 



# Create your models here.
