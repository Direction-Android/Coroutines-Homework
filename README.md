# Homework kotlin coroutines

### Change current implementation with coroutines

   1. Change return type in `MemesService` and add `suspend` modificator
   
   2. Rewrite logic in presenter from callbacks to coroutines
        
   3. Create custom scope for presenter: PresenterScope with `MainDispatcher` and `CoroutineName("PresenterCoroutine")`
    
   4. Catch exceptions with the help of `CoroutineExceptionHanlder`
   
   5. Do not forget to cancel Job in `onStop()`

