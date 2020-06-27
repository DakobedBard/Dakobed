#
# new_notes_json = "fileID{}/fileID{}Notes.json".format(i,i)
# with open('data/dakobed-tabs/fileID{}.json'.format(i), "rb") as f:
#     s3.upload_fileobj(f, bucket, "fileID{}/{}notes.json".format(i,i))
# with open(wav, "rb") as f:
#     s3.upload_fileobj(f, bucket, "fileID{}/audio.wav".format(i))
#
# path_ = 'data/guitarset/fileID{}'.format(i)
# if not os.path.isdir(path_):
#     os.mkdir(path_)
# print("Saved notes JSON & wav file {}".format(i))
#
# y, sr = librosa.load(wav)
# cqt = librosa.amplitude_to_db(
#     np.abs(librosa.core.cqt(y, sr=sr, n_bins=144, bins_per_octave=36, fmin=librosa.note_to_hz('C2'), norm=1))).T
# notes = jam_to_notes_matrix(jam)
# binary_annotation_matrix, multivariable_annotation_matrix = (notes, y.shape[0])
#
# uploadfiles = [('data/guitarset/fileID{}/cqt.npy'.format(i), cqt, 'fileID{}/cqt.npy'.format(i)),
#                ('data/guitarset/fileID{}/binary_annotation.npy'.format(i), binary_annotation_matrix, 'fileID{}/binaryAnnotation.npy'.format(i)),
#                ('data/guitarset/fileID{}/multivariable_annotation.npy'.format(i), multivariable_annotation_matrix, 'fileID{}/multivarAnnotaion.npy'.format(i))]
#
#
# for upload in uploadfiles:
#
#     file, array, s3path = upload[0], upload[1],upload[2]
#     np.save(file, arr=array)
#     with open(file, "rb") as f:
#         s3.upload_fileobj(f, bucket, s3path)
# print("saved transforms")

