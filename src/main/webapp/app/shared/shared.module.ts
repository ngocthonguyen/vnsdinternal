import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { VnsdinternalSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [VnsdinternalSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [VnsdinternalSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VnsdinternalSharedModule {
  static forRoot() {
    return {
      ngModule: VnsdinternalSharedModule
    };
  }
}
