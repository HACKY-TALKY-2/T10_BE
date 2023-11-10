# Generated by Django 4.2.4 on 2023-11-10 13:38

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='User',
            fields=[
                ('member_key_id', models.IntegerField(auto_created=True, unique=True, verbose_name='멤버번호')),
                ('last_login', models.DateTimeField(blank=True, null=True, verbose_name='last login')),
                ('user_id', models.CharField(max_length=40, primary_key=True, serialize=False, unique=True, verbose_name='사용자 계정')),
                ('password', models.TextField(verbose_name='비밀번호')),
                ('point', models.IntegerField(default=0, verbose_name='포인트')),
                ('location', models.CharField(max_length=100, verbose_name='지역')),
                ('is_active', models.BooleanField(default=True)),
                ('is_admin', models.BooleanField(default=False)),
            ],
            options={
                'abstract': False,
            },
        ),
    ]
