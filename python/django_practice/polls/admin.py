from django.contrib import admin
from .models import Question,Choice
# Register your models here.



class ChoiceInline(admin.StackedInline):
    model = Choice
    extra = 3

class QuestionAdmin(admin.ModelAdmin):
    fieldsets = [
        ('test',{'fields':['question_text']}),
        ('Date information',{'fields':['pub_data']})
    ]
    inlines = [ChoiceInline]
    list_display = ('question_text','pub_data')
admin.site.register(Question,QuestionAdmin)

