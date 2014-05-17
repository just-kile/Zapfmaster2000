define([ 'Underscore'], function( _) {

    describe('just checking', function() {
        it('works for app', function() {

            expect(_.size([1,2,3])).toEqual(3);

        });


        it('works for underscore', function() {
            // just checking that _ works
            expect(_.size([1,2,3])).toEqual(3);
        });

    });

});
